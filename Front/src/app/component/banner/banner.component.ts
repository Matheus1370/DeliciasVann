import { Component, Input } from '@angular/core';

@Component({
  selector: 'app-banner',
  imports: [],
  templateUrl: './banner.component.html',
  styleUrl: './banner.component.css',
  inputs: ['title', 'buttonText', 'backgroundImageUrl', 'size', 'buttonLink'],
})
export class BannerComponent {
  @Input() title!: string;
  @Input() buttonText!: string;
  @Input() backgroundImageUrl!: string;
  @Input() size!: string;
  titleDisplay = 'none';
  buttonDisplay = 'none';
  buttonLink = '';

  ngOnInit() {
    this.titleDisplay = !!this.title ? 'inline' : 'none';
    this.buttonDisplay = !!this.buttonText ? 'block' : 'none';
    this.buttonLink = this.buttonLink || '#';
    console.log(this.backgroundImageUrl);
  }

  onButtonClick() {
    if (this.buttonLink) {
      window.location.href = this.buttonLink;
    }
  }
}

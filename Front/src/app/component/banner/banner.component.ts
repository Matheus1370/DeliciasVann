import { Component, Input } from '@angular/core';

@Component({
  selector: 'app-banner',
  imports: [],
  templateUrl: './banner.component.html',
  styleUrl: './banner.component.css',
  inputs: ['title', 'buttonText', 'backgroundImageUrl', 'size'],
})
export class BannerComponent {
  @Input() title!: string;
  @Input() buttonText!: string;
  @Input() backgroundImageUrl!: string;
  @Input() size!: string;
  titleDisplay = 'none';
  buttonDisplay = 'none';

  ngOnInit() {
    this.titleDisplay = !!this.title ? 'inline' : 'none';
    this.buttonDisplay = !!this.buttonText ? 'block' : 'none';
    console.log(this.backgroundImageUrl);
  }
}

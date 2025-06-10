import { Component, Input } from '@angular/core';

@Component({
  selector: 'app-vseparator',
  imports: [],
  inputs: ['half'],
  templateUrl: './vseparator.component.html',
  styleUrl: './vseparator.component.css',
})
export class VSeparatorComponent {
  @Input() half: boolean = false;

  get separatorClass(): string {
    return this.half ? 'v-separator-half' : 'v-separator-full';
  }
}

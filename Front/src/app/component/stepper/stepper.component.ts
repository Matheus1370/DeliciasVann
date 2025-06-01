import { Component, Input } from '@angular/core';

@Component({
  selector: 'app-stepper',
  imports: [],
  inputs: ['amount'],
  templateUrl: './stepper.component.html',
  styleUrl: './stepper.component.css',
})
export class StepperComponent {
  @Input() amount!: number;
  @Input() onChange!: (amount: number) => void;

  increment(): void {
    this.onChange(this.amount + 1);
  }

  onChangeAmount(event: any): void {
    const value = parseInt(event.target.value, 10);
    if (!isNaN(value) && value >= 0) {
      this.onChange(value);
    }
  }

  decrement(): void {
    this.onChange(this.amount - 1);
  }

  ngOnChanges(): void {
    console.log(this.amount);
  }
}

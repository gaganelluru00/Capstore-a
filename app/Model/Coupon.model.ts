export class Coupon {
  constructor(
    public couponEndDate: Date,
    public couponStartDate: Date,
    public couponAmount: number,
    public couponMinOrderAmount: number,
    public issusedBy: string
  ) {}
}

export class LocalStorageDataSource {
  private static readonly CART_KEY = 'cartItems';
  private static readonly CART_ITEM_KEY_PREFIX = 'cartItem_';
  private static readonly CART_ITEM_AMOUNT_KEY_SUFFIX = '_amount';

  static updateCartItems(items: { id: string; amount: number }[]): void {
    const cartItems: { [key: string]: number } = {};
    items.forEach((item) => {
      cartItems[
        `${this.CART_ITEM_KEY_PREFIX}${item.id}${this.CART_ITEM_AMOUNT_KEY_SUFFIX}`
      ] = item.amount;
    });
    if (typeof window !== 'undefined') {
      localStorage.setItem(this.CART_KEY, JSON.stringify(cartItems));
    }
  }

  static getCartItems(): { id: string; amount: number }[] {
    if (typeof window !== 'undefined') {
      const cartItems: { [key: string]: number } = JSON.parse(
        localStorage.getItem(this.CART_KEY) || '{}'
      );
      return Object.entries(cartItems).map(([key, amount]) => {
        const id = key
          .replace(this.CART_ITEM_KEY_PREFIX, '')
          .replace(this.CART_ITEM_AMOUNT_KEY_SUFFIX, '');
        return { id, amount };
      });
    } else {
      return [];
    }
  }
}

export interface Restaurant {
  id: string;
  name: string;
  cuisine: string;
  rating: number;
  deliveryTime: string;
  costForTwo: number;
  image: string;
  offers?: string[];
  promoted?: boolean;
}

export interface FoodItem {
  id: string;
  name: string;
  description: string;
  price: number;
  image: string;
  category: string;
  restaurantId: string;
}

export interface CartItem extends FoodItem {
  quantity: number;
}

'use client';

import { Plus, Minus } from 'lucide-react';
import { FoodItem as FoodItemType } from '@/types';
import { useCart } from '@/context/CartContext';

interface FoodItemProps {
  item: FoodItemType;
}

export default function FoodItem({ item }: FoodItemProps) {
  const { addToCart, removeFromCart, getItemQuantity } = useCart();
  const quantity = getItemQuantity(item.id);

  return (
    <div className="bg-white rounded-lg p-4 shadow-sm border border-gray-200 flex items-center justify-between">
      <div className="flex-1">
        <h3 className="font-semibold text-gray-800 mb-1">{item.name}</h3>
        <p className="text-sm text-gray-600 mb-2">{item.description}</p>
        <p className="text-lg font-semibold text-gray-800">₹{item.price}</p>
      </div>
      <div className="ml-4 flex items-center space-x-3">
        {quantity > 0 ? (
          <div className="flex items-center space-x-2">
            <button
              onClick={() => removeFromCart(item.id)}
              className="w-8 h-8 rounded-full bg-primary text-white flex items-center justify-center hover:bg-orange-600"
            >
              <Minus className="w-4 h-4" />
            </button>
            <span className="w-8 text-center font-semibold">{quantity}</span>
            <button
              onClick={() => addToCart(item)}
              className="w-8 h-8 rounded-full bg-primary text-white flex items-center justify-center hover:bg-orange-600"
            >
              <Plus className="w-4 h-4" />
            </button>
          </div>
        ) : (
          <button
            onClick={() => addToCart(item)}
            className="px-4 py-2 bg-primary text-white rounded-lg hover:bg-orange-600 transition font-semibold"
          >
            ADD
          </button>
        )}
      </div>
    </div>
  );
}

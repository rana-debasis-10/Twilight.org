'use client';

import { Plus, Minus, X } from 'lucide-react';
import { CartItem as CartItemType } from '@/types';
import { useCart } from '@/context/CartContext';

interface CartItemProps {
  item: CartItemType;
}

export default function CartItem({ item }: CartItemProps) {
  const { addToCart, removeFromCart, removeItem } = useCart();

  return (
    <div className="flex items-center space-x-4 p-3 border border-gray-200 rounded-lg">
      <img
        src={item.image}
        alt={item.name}
        className="w-16 h-16 object-cover rounded"
      />
      <div className="flex-1">
        <h3 className="font-semibold text-sm">{item.name}</h3>
        <p className="text-sm text-gray-600">₹{item.price}</p>
      </div>
      <div className="flex items-center space-x-2">
        <button
          onClick={() => removeFromCart(item.id)}
          className="w-7 h-7 rounded-full border border-gray-300 flex items-center justify-center hover:bg-gray-100"
        >
          <Minus className="w-4 h-4" />
        </button>
        <span className="w-8 text-center font-semibold">{item.quantity}</span>
        <button
          onClick={() => addToCart(item)}
          className="w-7 h-7 rounded-full border border-gray-300 flex items-center justify-center hover:bg-gray-100"
        >
          <Plus className="w-4 h-4" />
        </button>
      </div>
      <button
        onClick={() => removeItem(item.id)}
        className="p-2 hover:bg-gray-100 rounded-full"
      >
        <X className="w-4 h-4 text-gray-500" />
      </button>
    </div>
  );
}

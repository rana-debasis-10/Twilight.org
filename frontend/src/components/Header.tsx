'use client';

import { ShoppingCart, MapPin, User } from 'lucide-react';
import { useCart } from '@/context/CartContext';

interface HeaderProps {
  onCartClick: () => void;
}

export default function Header({ onCartClick }: HeaderProps) {
  const { cartItems } = useCart();
  const itemCount = cartItems.reduce((sum, item) => sum + item.quantity, 0);

  return (
    <header className="bg-white shadow-md sticky top-0 z-50">
      <div className="max-w-7xl mx-auto px-4 py-4">
        <div className="flex items-center justify-between">
          <div className="flex items-center space-x-8">
            <div className="flex items-center space-x-2">
              <div className="w-10 h-10 bg-primary rounded-lg flex items-center justify-center">
                <span className="text-white font-bold text-xl">S</span>
              </div>
              <span className="text-2xl font-bold text-gray-800">Swiggy</span>
            </div>
            <div className="hidden md:flex items-center space-x-2 text-gray-600">
              <MapPin className="w-5 h-5" />
              <span className="text-sm">
                <span className="font-semibold">Deliver to</span> 123 Main St, City
              </span>
            </div>
          </div>
          <div className="flex items-center space-x-4">
            <button className="hidden md:flex items-center space-x-2 px-4 py-2 text-gray-700 hover:bg-gray-100 rounded-lg">
              <User className="w-5 h-5" />
              <span>Sign In</span>
            </button>
            <button
              onClick={onCartClick}
              className="relative flex items-center space-x-2 px-4 py-2 bg-primary text-white rounded-lg hover:bg-orange-600 transition"
            >
              <ShoppingCart className="w-5 h-5" />
              <span>Cart</span>
              {itemCount > 0 && (
                <span className="absolute -top-2 -right-2 bg-red-500 text-white text-xs rounded-full w-6 h-6 flex items-center justify-center">
                  {itemCount}
                </span>
              )}
            </button>
          </div>
        </div>
      </div>
    </header>
  );
}

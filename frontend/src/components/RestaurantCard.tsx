'use client';

import { Clock, Star, IndianRupee } from 'lucide-react';
import { Restaurant } from '@/types';
import Link from 'next/link';

interface RestaurantCardProps {
  restaurant: Restaurant;
}

export default function RestaurantCard({ restaurant }: RestaurantCardProps) {
  return (
    <Link href={`/restaurant/${restaurant.id}`}>
      <div className="bg-white rounded-lg shadow-md overflow-hidden hover:shadow-xl transition-shadow cursor-pointer">
        <div className="relative">
          <img
            src={restaurant.image}
            alt={restaurant.name}
            className="w-full h-48 object-cover"
          />
          {restaurant.promoted && (
            <div className="absolute top-2 left-2 bg-primary text-white px-2 py-1 rounded text-xs font-semibold">
              PROMOTED
            </div>
          )}
          {restaurant.offers && restaurant.offers.length > 0 && (
            <div className="absolute bottom-2 left-2 bg-secondary text-white px-2 py-1 rounded text-xs font-semibold">
              {restaurant.offers[0]}
            </div>
          )}
        </div>
        <div className="p-4">
          <h3 className="text-lg font-semibold text-gray-800 mb-1">
            {restaurant.name}
          </h3>
          <p className="text-sm text-gray-600 mb-2">{restaurant.cuisine}</p>
          <div className="flex items-center justify-between text-sm">
            <div className="flex items-center space-x-4">
              <div className="flex items-center space-x-1">
                <Star className="w-4 h-4 fill-yellow-400 text-yellow-400" />
                <span className="font-semibold">{restaurant.rating}</span>
              </div>
              <div className="flex items-center space-x-1 text-gray-600">
                <Clock className="w-4 h-4" />
                <span>{restaurant.deliveryTime}</span>
              </div>
              <div className="flex items-center space-x-1 text-gray-600">
                <IndianRupee className="w-4 h-4" />
                <span>{restaurant.costForTwo} for two</span>
              </div>
            </div>
          </div>
        </div>
      </div>
    </Link>
  );
}

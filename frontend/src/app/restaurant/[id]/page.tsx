'use client';

import { useMemo } from 'react';
import { useParams } from 'next/navigation';
import Header from '@/components/Header';
import Footer from '@/components/Footer';
import { sampleRestaurants, sampleFoodItems } from '@/utils/constants';
import FoodItem from '@/components/FoodItem';

export default function RestaurantDetailPage() {
  const params = useParams();
  const id = String(params?.id || '');
  const restaurant = useMemo(() => sampleRestaurants.find(r => r.id === id), [id]);
  const items = useMemo(() => sampleFoodItems.filter(f => f.restaurantId === id), [id]);

  if (!restaurant) {
    return (
      <div className="min-h-screen bg-gray-50">
        <Header onCartClick={() => {}} />
        <div className="max-w-5xl mx-auto px-4 py-12">
          <p className="text-gray-600">Restaurant not found.</p>
        </div>
        <Footer />
      </div>
    );
  }

  return (
    <div className="min-h-screen bg-gray-50">
      <Header onCartClick={() => {}} />
      <div className="w-full bg-white shadow-sm">
        <div className="max-w-5xl mx-auto px-4 py-6">
          <div className="flex gap-6">
            <img src={restaurant.image} alt={restaurant.name} className="w-40 h-40 object-cover rounded" />
            <div>
              <h1 className="text-3xl font-bold">{restaurant.name}</h1>
              <p className="text-gray-600 mt-1">{restaurant.cuisine}</p>
              <p className="text-gray-600 mt-1">{restaurant.deliveryTime} • ₹{restaurant.costForTwo} for two</p>
              {restaurant.offers?.length ? (
                <div className="mt-3 flex flex-wrap gap-2">
                  {restaurant.offers.map((o, i) => (
                    <span key={i} className="text-xs bg-orange-50 text-orange-700 px-2 py-1 rounded border border-orange-200">{o}</span>
                  ))}
                </div>
              ) : null}
            </div>
          </div>
        </div>
      </div>

      <div className="max-w-5xl mx-auto px-4 py-8">
        <h2 className="text-xl font-semibold mb-4">Menu</h2>
        {items.length === 0 ? (
          <p className="text-gray-600">Menu coming soon.</p>
        ) : (
          <div className="grid grid-cols-1 md:grid-cols-2 gap-4">
            {items.map(item => (
              <FoodItem key={item.id} item={item} />
            ))}
          </div>
        )}
      </div>
      <Footer />
    </div>
  );
}

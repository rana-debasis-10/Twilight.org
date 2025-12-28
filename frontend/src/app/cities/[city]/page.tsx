'use client';

import { useParams } from 'next/navigation';
import Header from '@/components/Header';
import Footer from '@/components/Footer';
import RestaurantList from '@/components/RestaurantList';
import { sampleRestaurants } from '@/utils/constants';

export default function CityPage() {
  const params = useParams();
  const city = String(params?.city || '').replace(/\b\w/g, (c) => c.toUpperCase());

  return (
    <div className="min-h-screen bg-gray-50">
      <Header onCartClick={() => {}} />
      <div className="max-w-7xl mx-auto px-4 py-6">
        <h1 className="text-2xl font-bold mb-4">Restaurants in {city}</h1>
        <RestaurantList restaurants={sampleRestaurants} />
      </div>
      <Footer />
    </div>
  );
}

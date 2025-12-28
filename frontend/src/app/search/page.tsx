'use client';

import { useMemo, useState } from 'react';
import Header from '@/components/Header';
import Footer from '@/components/Footer';
import SearchBar from '@/components/SearchBar';
import FilterBar from '@/components/FilterBar';
import RestaurantList from '@/components/RestaurantList';
import { sampleRestaurants } from '@/utils/constants';

export default function SearchPage() {
  const [query, setQuery] = useState('');
  const restaurants = useMemo(() => {
    if (!query.trim()) return sampleRestaurants;
    return sampleRestaurants.filter((r) =>
      r.name.toLowerCase().includes(query.toLowerCase()) ||
      r.cuisine.toLowerCase().includes(query.toLowerCase())
    );
  }, [query]);

  return (
    <div className="min-h-screen bg-gray-50">
      <Header onCartClick={() => {}} />
      <div className="max-w-7xl mx-auto px-4 py-6">
        <h1 className="text-2xl font-bold mb-4">Search Restaurants</h1>
        <SearchBar onSearch={setQuery} />
        <FilterBar />
        <RestaurantList restaurants={restaurants} />
      </div>
      <Footer />
    </div>
  );
}

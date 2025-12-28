'use client';

import { useState } from 'react';
import Header from '@/components/Header';
import SearchBar from '@/components/SearchBar';
import FilterBar from '@/components/FilterBar';
import RestaurantList from '@/components/RestaurantList';
import Cart from '@/components/Cart';
import { sampleRestaurants } from '@/utils/constants';
import { Restaurant } from '@/types';

export default function Home() {
  const [searchQuery, setSearchQuery] = useState('');
  const [filteredRestaurants, setFilteredRestaurants] = useState(sampleRestaurants);
  const [showCart, setShowCart] = useState(false);

  const handleSearch = (query: string) => {
    setSearchQuery(query);
    if (query.trim() === '') {
      setFilteredRestaurants(sampleRestaurants);
    } else {
      const filtered = sampleRestaurants.filter(
        (restaurant) =>
          restaurant.name.toLowerCase().includes(query.toLowerCase()) ||
          restaurant.cuisine.toLowerCase().includes(query.toLowerCase())
      );
      setFilteredRestaurants(filtered);
    }
  };

  return (
    <div className="min-h-screen bg-gray-50">
      <Header onCartClick={() => setShowCart(true)} />
      <div className="max-w-7xl mx-auto px-4 py-6">
        <SearchBar onSearch={handleSearch} />
        <FilterBar />
        <RestaurantList restaurants={filteredRestaurants} />
      </div>
      {showCart && <Cart onClose={() => setShowCart(false)} />}
    </div>
  );
}

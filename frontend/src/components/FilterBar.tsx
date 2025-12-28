'use client';

import { useState } from 'react';

const filters = [
  { id: 'all', label: 'All' },
  { id: 'rating', label: 'Rating 4.0+' },
  { id: 'fast', label: 'Fast Delivery' },
  { id: 'offers', label: 'Offers' },
  { id: 'veg', label: 'Pure Veg' },
];

export default function FilterBar() {
  const [activeFilter, setActiveFilter] = useState('all');

  return (
    <div className="mb-6 overflow-x-auto">
      <div className="flex space-x-4 pb-2">
        {filters.map((filter) => (
          <button
            key={filter.id}
            onClick={() => setActiveFilter(filter.id)}
            className={`px-4 py-2 rounded-full whitespace-nowrap transition ${
              activeFilter === filter.id
                ? 'bg-primary text-white'
                : 'bg-white text-gray-700 hover:bg-gray-100'
            }`}
          >
            {filter.label}
          </button>
        ))}
      </div>
    </div>
  );
}

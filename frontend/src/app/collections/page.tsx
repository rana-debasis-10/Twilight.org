'use client';

import Header from '@/components/Header';
import Footer from '@/components/Footer';
import Link from 'next/link';
import { sampleRestaurants } from '@/utils/constants';

const sampleCollections = [
  { id: 'promoted', title: 'Promoted', filter: (id: string) => sampleRestaurants.find(r => r.id === id)?.promoted },
  { id: 'top-rated', title: 'Top Rated 4.3+', filter: (id: string) => (sampleRestaurants.find(r => r.id === id)?.rating || 0) >= 4.3 },
];

export default function CollectionsPage() {
  return (
    <div className="min-h-screen bg-gray-50">
      <Header onCartClick={() => {}} />
      <div className="max-w-7xl mx-auto px-4 py-6">
        <h1 className="text-2xl font-bold mb-6">Collections</h1>
        <div className="grid grid-cols-1 md:grid-cols-3 gap-6">
          {sampleCollections.map((c) => (
            <Link key={c.id} href={`/search?collection=${c.id}`} className="bg-white rounded-lg shadow p-6 hover:shadow-md transition border border-gray-100">
              <div className="text-xl font-semibold mb-2">{c.title}</div>
              <div className="text-gray-600 text-sm">Explore restaurants</div>
            </Link>
          ))}
        </div>
      </div>
      <Footer />
    </div>
  );
}

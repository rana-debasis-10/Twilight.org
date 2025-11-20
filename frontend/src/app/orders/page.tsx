'use client';

import Header from '@/components/Header';
import Footer from '@/components/Footer';

const mockOrders = [
  { id: 'o1', restaurant: 'Burger King', date: '2025-10-10', total: 398, status: 'Delivered' },
  { id: 'o2', restaurant: 'Pizza Hut', date: '2025-10-05', total: 299, status: 'Delivered' },
];

export default function OrdersPage() {
  return (
    <div className="min-h-screen bg-gray-50">
      <Header onCartClick={() => {}} />
      <div className="max-w-5xl mx-auto px-4 py-6">
        <h1 className="text-2xl font-bold mb-6">Your Orders</h1>
        <div className="space-y-4">
          {mockOrders.map(o => (
            <div key={o.id} className="bg-white border rounded p-4 flex justify-between">
              <div>
                <div className="font-semibold">{o.restaurant}</div>
                <div className="text-sm text-gray-600">{o.date}</div>
              </div>
              <div className="text-right">
                <div className="font-semibold">₹{o.total}</div>
                <div className="text-sm text-green-600">{o.status}</div>
              </div>
            </div>
          ))}
        </div>
      </div>
      <Footer />
    </div>
  );
}

'use client';

import Cart from '@/components/Cart';
import { useState } from 'react';
import Header from '@/components/Header';
import Footer from '@/components/Footer';

export default function CartPage() {
  const [open, setOpen] = useState(true);

  return (
    <div className="min-h-screen bg-gray-50">
      <Header onCartClick={() => setOpen(true)} />
      <div className="max-w-7xl mx-auto px-4 py-6">
        <h1 className="text-2xl font-bold mb-4">Your Cart</h1>
        <p className="text-gray-600 mb-2">This is a full-page view. You can still open the cart drawer.</p>
      </div>
      {open && <Cart onClose={() => setOpen(false)} />}
      <Footer />
    </div>
  );
}

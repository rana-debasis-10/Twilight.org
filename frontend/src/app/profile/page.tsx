'use client';

import Header from '@/components/Header';
import Footer from '@/components/Footer';
import { useAuth } from '@/context/AuthContext';
import Link from 'next/link';

export default function ProfilePage() {
  const { user, logout } = useAuth();

  return (
    <div className="min-h-screen bg-gray-50">
      <Header onCartClick={() => {}} />
      <div className="max-w-3xl mx-auto px-4 py-6">
        <h1 className="text-2xl font-bold mb-6">Profile</h1>
        {!user ? (
          <div className="bg-white border rounded p-6">
            <p className="text-gray-700">You are not signed in.</p>
            <div className="mt-4 flex gap-3">
              <Link href="/login" className="px-4 py-2 bg-primary text-white rounded">Login</Link>
              <Link href="/signup" className="px-4 py-2 border rounded">Sign Up</Link>
            </div>
          </div>
        ) : (
          <div className="bg-white border rounded p-6">
            <div className="font-semibold text-lg">{user.name}</div>
            <div className="text-gray-600">{user.email}</div>
            <button onClick={logout} className="mt-4 px-4 py-2 border rounded">Logout</button>
          </div>
        )}
      </div>
      <Footer />
    </div>
  );
}

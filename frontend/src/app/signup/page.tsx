'use client';

import { useState } from 'react';
import Header from '@/components/Header';
import Footer from '@/components/Footer';
import { useAuth } from '@/context/AuthContext';
import { useRouter } from 'next/navigation';

export default function SignupPage() {
  const { signup } = useAuth();
  const router = useRouter();
  const [name, setName] = useState('');
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');

  const onSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    await signup(name, email, password);
    router.push('/profile');
  };

  return (
    <div className="min-h-screen bg-gray-50">
      <Header onCartClick={() => {}} />
      <div className="max-w-md mx-auto px-4 py-10">
        <h1 className="text-2xl font-bold mb-6">Sign Up</h1>
        <form onSubmit={onSubmit} className="bg-white border rounded p-6 space-y-4">
          <input className="w-full border rounded px-3 py-2" placeholder="Name" value={name} onChange={(e) => setName(e.target.value)} />
          <input className="w-full border rounded px-3 py-2" placeholder="Email" value={email} onChange={(e) => setEmail(e.target.value)} />
          <input className="w-full border rounded px-3 py-2" placeholder="Password" type="password" value={password} onChange={(e) => setPassword(e.target.value)} />
          <button className="w-full bg-primary text-white py-2 rounded">Create account</button>
        </form>
      </div>
      <Footer />
    </div>
  );
}

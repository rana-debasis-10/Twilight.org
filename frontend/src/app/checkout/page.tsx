'use client';

import Header from '@/components/Header';
import Footer from '@/components/Footer';
import { useCart } from '@/context/CartContext';

export default function CheckoutPage() {
  const { cartItems, totalPrice, clearCart } = useCart();

  return (
    <div className="min-h-screen bg-gray-50">
      <Header onCartClick={() => {}} />
      <div className="max-w-5xl mx-auto px-4 py-6">
        <h1 className="text-2xl font-bold mb-6">Checkout</h1>
        {cartItems.length === 0 ? (
          <p className="text-gray-600">Your cart is empty.</p>
        ) : (
          <div className="grid grid-cols-1 md:grid-cols-3 gap-6">
            <div className="md:col-span-2 space-y-4">
              {cartItems.map((i) => (
                <div key={i.id} className="p-4 bg-white border rounded flex justify-between">
                  <div>
                    <div className="font-semibold">{i.name}</div>
                    <div className="text-sm text-gray-600">Qty: {i.quantity}</div>
                  </div>
                  <div className="font-semibold">₹{i.price * i.quantity}</div>
                </div>
              ))}
            </div>
            <div className="space-y-4">
              <div className="p-4 bg-white border rounded">
                <div className="flex justify-between mb-2"><span>Subtotal</span><span>₹{totalPrice}</span></div>
                <div className="flex justify-between mb-2 text-gray-600 text-sm"><span>Delivery</span><span>₹0</span></div>
                <div className="flex justify-between font-semibold text-lg"><span>Total</span><span>₹{totalPrice}</span></div>
                <button
                  onClick={() => { alert('Order placed! (demo)'); clearCart(); }}
                  className="w-full mt-4 bg-primary text-white py-3 rounded-lg font-semibold hover:bg-orange-600"
                >Place Order</button>
              </div>
            </div>
          </div>
        )}
      </div>
      <Footer />
    </div>
  );
}

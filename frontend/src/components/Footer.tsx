export default function Footer() {
  return (
    <footer className="bg-white border-t border-gray-200 mt-12">
      <div className="max-w-7xl mx-auto px-4 py-8 text-sm text-gray-600 flex flex-col md:flex-row md:items-center md:justify-between gap-3">
        <div className="font-semibold text-gray-800">Swiggy Clone</div>
        <div>© {new Date().getFullYear()} Demo app for learning purposes.</div>
        <div className="flex gap-4">
          <a className="hover:text-gray-800" href="#">Privacy</a>
          <a className="hover:text-gray-800" href="#">Terms</a>
          <a className="hover:text-gray-800" href="#">Contact</a>
        </div>
      </div>
    </footer>
  );
}

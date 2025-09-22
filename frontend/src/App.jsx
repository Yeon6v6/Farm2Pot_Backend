import { useEffect, useState } from 'react'

import { BrowserRouter as Router, Routes, Route } from 'react-router-dom'
import Header from './components/Header'
import Footer from './components/Footer'
import Home from './pages/Home'
import ProductList from './pages/ProductList'
import ProductDetail from './pages/ProductDetail'

import userClient from './api/userClient'
import adminClient from './api/adminClient'
import subscriptionClient from './api/subscriptionClient'
import SignIn from './pages/SignIn'
import Signup from './pages/Signup'

function App() {
  const [testword, setTestword] = useState()
  return (
    <Router>
      <Header />
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/signIn" element={<SignIn />} />
        <Route path="/signUp" element={<Signup />} />
        <Route path="/products" element={<ProductList />} />
        <Route path="/products/:id" element={<ProductDetail />} />
      </Routes>
      <Footer />
    </Router>
  )
}

export default App

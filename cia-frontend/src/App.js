import React, { useState } from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import './App.css';
import Home from './components/Home';
import Login from './components/Login';

function App() {
  const [user, setUser] = useState([]);

  return (
    <Router>
      <Routes>
        <Route
          path="/"
          element={user.length > 0 ? <Home user={user} /> : <Login setuser={setUser} />}
        />
      </Routes>
    </Router>
  );
}

export default App;

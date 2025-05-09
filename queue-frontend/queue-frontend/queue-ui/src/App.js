import React from 'react';
import './App.css';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import EmployeeDashboard from './components/employee/EmployeeDashboard';
import EmployeeLogin from './components/employee/EmployeeLogin';
import QueueListPage from './components/employee/QueueListPage';
import CheckStatus from './components/Queue/CheckStatus';
import JoinQueue from './components/Queue/JoinQueue';
import Home from './components/Queue/Home';

function App() {
  return (
    <Router>
      <div className="App">
        <Routes>
          <Route path="/" element={<Home />} />
          <Route path="/dashboard" element={<EmployeeDashboard />} />
          <Route path="/join" element={<JoinQueue />} />
          <Route path="/status" element={<CheckStatus />} />
          <Route path="/queue-list" element={<QueueListPage />} />
          <Route path="/employee-login" element={<EmployeeLogin />} />
        </Routes>
      </div>
    </Router>
  );
}

export default App;

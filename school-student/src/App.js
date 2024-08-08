import logo from './logo.svg';
import './App.css';
import Home from './Component/Home';
import SchoolComponent from './Component/SchoolComponent';
import { BrowserRouter, Route, Router, Routes } from 'react-router-dom';
import StudentComponent from './Component/StudentComponent';
import NavBar from './Component/NavBar';

function App() {
  return (
    <div className="App">
      <BrowserRouter>
      <NavBar/>
      <Routes>
          <Route path='/' element= {<Home/>} /> 
          <Route path='/school' element = {<SchoolComponent/>} />  
          <Route path='/student' element = {<StudentComponent/>} />
      </Routes>
      </BrowserRouter>
      {/* <Home/>
      <SchoolComponent/> */}
    </div>
  );
}

export default App;

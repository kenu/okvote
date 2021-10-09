import React from 'react';

const Header = () => {
  return (
    <header>
      <h1>OkVote</h1>
      <nav>
        <a href="/">Home</a> |
        <a href="/login">Login</a> |
        <a href="/result">Result</a> |
        <a href="/list">List</a>
      </nav>
      <hr></hr>    
    </header>
  );
};

export default Header;
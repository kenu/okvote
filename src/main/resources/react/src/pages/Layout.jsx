import React from 'react';
import Header from './fragments/Header'
import Footer from './fragments/Footer'

const Layout = ({children}) => {
  return (
    <div id='wrap'>
      <Header />
      <section>{children}</section>
      <Footer />
    </div>
  );
};

export default Layout;
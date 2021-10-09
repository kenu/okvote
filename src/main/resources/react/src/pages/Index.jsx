import React from 'react';

const Index = () => {
  return (
    <div>
      <h2>Q</h2>
      <div className="question">스프링 프레임워크는 자바를 몰라도 개발할 수 있다</div>
      <h2>A</h2>
      <div className="answers">
        <button className="answer">있다</button>
        <button className="answer">없다</button>
      </div>
    </div>
  );
};

export default Index;
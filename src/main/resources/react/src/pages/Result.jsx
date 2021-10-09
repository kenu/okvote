import React from 'react';

const Result = () => {
  return (
    <div>
      <h2>Q</h2>
      <div className="question">스프링 프레임워크는 자바를 몰라도 개발할 수 있다</div>
      <h2>A</h2>
      <div className="answers">
        <div className="answered">있다 10 (33%)</div>
        <div className="answered">있다 20 (67%)</div>
      </div>
    </div>
  );
};

export default Result;
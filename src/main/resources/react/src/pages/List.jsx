import React from 'react';

const List = () => {
  const questions = ['Q 스프링 프레임워크는 자바를 몰라도 개발할 수 있다', 
                    'Q 스프링 프레임워크는 자바를 몰라도 개발할 수 있다', 
                    'Q 스프링 프레임워크는 자바를 몰라도 개발할 수 있다', 
                    'Q 스프링 프레임워크는 자바를 몰라도 개발할 수 있다', 
                    'Q 스프링 프레임워크는 자바를 몰라도 개발할 수 있다']

  const questionList = questions.map((question, index) => (
    <div key={index} className="question">{question}</div>
  ))
  return (
    <div>{questionList}</div>
  );
};

export default List;
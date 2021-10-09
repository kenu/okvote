import React from 'react';

const Login = () => {
  return (
    <div>
    <form method="POST" id="login-form">
      <input type="text" name="id" id="id" />
      <button class="login">로그인</button>
    </form>
    </div>
  );
};

export default Login;
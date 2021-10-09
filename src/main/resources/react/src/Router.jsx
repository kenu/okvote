import React from 'react'
import { BrowserRouter, Switch, Route} from 'react-router-dom'
import Layout from './pages/Layout'
import Index from './pages/Index'
import Login from './pages/Login'
import Result from './pages/Result'
import List from './pages/List'

function Router() {
  return (
    <BrowserRouter>
      <Layout>
        <Switch>
          <Route exact path='/' component={Index} />
          <Route exact path='/login' component={Login} />
          <Route exact path='/result' component={Result} />
          <Route exact path='/list' component={List} />
        </Switch>
      </Layout>
    </BrowserRouter>
  )
}

export default Router

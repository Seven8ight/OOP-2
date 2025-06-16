import { BrowserRouter, Route, Routes } from "react-router";
import Products from "./Components/Products";
import Accounts from "./Components/Account";
import Ppage from "./Components/ProductPage";
import Transact from "./Components/Transact";
import Branch from "./Components/Branch";
import Main from "./Components/Main";
import Landing from "./Components/Landing";
import User from "./Components/User";

const App = (): React.ReactNode => {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" Component={Landing} index />
        <Route path="/user" Component={User} />
        <Route path="/accounts" Component={Accounts} />
        <Route path="/products" Component={Products} />
        <Route path="/product/:id" Component={Ppage} />
        <Route path="/transact" Component={Transact} />
        <Route path="/management/branch/:id" Component={Branch} />
        <Route path="/management/admin" Component={Main} />
      </Routes>
    </BrowserRouter>
  );
};

export default App;

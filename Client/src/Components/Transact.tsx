import { useNavigate } from "react-router";

const Transact = (): React.ReactNode => {
  const navigation = useNavigate();

  return (
    <div id="Transact">
      <div id="previous">
        <button onClick={() => navigation("/products")}>
          <i className="fa-solid fa-arrow-left"></i>
        </button>
      </div>

      <div id="header">
        <h1>Transactions</h1>
        <p>Products chosen for purchase</p>
      </div>
      <div id="products">
        <div id="product">
          <h1>Coca cola</h1>
          <div>
            <p>Quantity</p>
            <p>Price</p>
            <p>Delete</p>
          </div>
        </div>
      </div>
      <div id="submission">
        <button>Check out</button>
      </div>
    </div>
  );
};

export default Transact;

import { useNavigate } from "react-router";
import productImg from "./../Assets/joel-filipe-VuwAfoHpxgs-unsplash.jpg";

const Ppage = (): React.ReactNode => {
  // const { id } = useParams<{ id: string }>(),
  const navigation = useNavigate();

  return (
    <div id="ProductPage">
      <header id="header">
        <div id="back">
          <button onClick={() => navigation("/products")}>
            <i className="fa-solid fa-arrow-left"></i>
          </button>
        </div>
        <h1>High Rise</h1>
      </header>
      <div id="content">
        <div id="productdesc">
          <div id="image">
            <img src={productImg} />
          </div>
          <div id="desc">
            <h1>Product name</h1>
            <div id="innerdet">
              <p>Desc</p>
              <p>Price</p>
            </div>
          </div>
        </div>
        <div id="sidebar">
          <div id="productPurchase">
            <p>Currently 11 in stock</p>
            <div id="stock">
              <button>+</button>
              <p>11</p>
              <button>-</button>
            </div>
            <br />
            <button id="add">Add to Cart</button>
          </div>

          <div id="cart">
            <h3>Cart</h3>
            <div id="items"></div>
            <div id="submit">
              <button onClick={() => navigation("/transact")}>
                Purchase items
              </button>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default Ppage;

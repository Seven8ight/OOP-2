import { useRef, useState } from "react";
import BackgroundImg from "./../Assets/ishan-seefromthesky-6U-sSfBV-gM-unsplash.jpg";
import productImg from "./../Assets/joel-filipe-VuwAfoHpxgs-unsplash.jpg";
import { useNavigate } from "react-router";

const Products = (): React.ReactNode => {
  const [branch, setBranch] = useState<
      "Nairobi" | "Machakos" | "Kisumu" | "Mombasa"
    >("Nairobi"),
    stickyNavRef = useRef<HTMLDivElement>(null),
    navigation = useNavigate();

  if (stickyNavRef.current) {
    stickyNavRef.current.addEventListener("click", () => {
      if (stickyNavRef.current) {
        if (stickyNavRef.current.classList.contains("open"))
          stickyNavRef.current.classList.remove("open");
        else stickyNavRef.current.classList.add("open");
      }
    });
    ``;
  }

  return (
    <div id="Products">
      <nav>
        <header>
          <h1>High Rise</h1>
        </header>
        <div>
          <i
            onClick={() => navigation("/user")}
            className="fa-solid fa-user"
          ></i>
        </div>
      </nav>
      <div id="container">
        <div id="main-Product">
          <div id="intro">
            <h2>Featured product of the day</h2>
            <p>
              Grab yourself a <i className="fa-solid fa-arrow-right"></i>
            </p>
          </div>

          <div id="card">
            <img src={BackgroundImg} />
            <div id="Text">
              <h2>Product Name</h2>
              <div id="extra">
                <p>Product desc</p>
                <p>Price</p>
              </div>

              <button onClick={() => navigation("/product/1234")}>Buy</button>
            </div>
          </div>
          <div id="line" />
        </div>
        <div id="otherProducts">
          <div id="product">
            <img src={productImg} />
            <div id="product-info">
              <h2>Product name</h2>
              <div id="extra">
                <p>Product desc</p>
                <p>Price</p>
              </div>
              <button onClick={() => navigation("/product/1234")}>Buy</button>
            </div>
          </div>
        </div>
      </div>
      <footer>
        <button
          onClick={() => {
            navigation("/management/admin");
            console.log("Pressed here");
          }}
        >
          Admin page
        </button>
        <button
          onClick={() => {
            navigation("/management/admin");
            console.log("Pressed here");
          }}
        >
          Branch page
        </button>
      </footer>
      <div id="stickynav">
        <p>{branch}</p>
        <div id="branches" ref={stickyNavRef}>
          <button onClick={() => setBranch("Nairobi")}>Nairobi</button>
          <button onClick={() => setBranch("Mombasa")}>Mombasa</button>
          <button onClick={() => setBranch("Kisumu")}>Kisumu</button>
          <button onClick={() => setBranch("Machakos")}>Machakos</button>
        </div>
      </div>
    </div>
  );
};

export default Products;

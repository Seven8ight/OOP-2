import { useNavigate } from "react-router";

const date: Date = new Date();

const Branch = (): React.ReactNode => {
  const navigation = useNavigate();

  return (
    <div id="Branch">
      <div id="back">
        <button
          onClick={() => {
            console.log("Clicked");
            navigation("/products");
          }}
        >
          <i className="fa-solid fa-arrow-left"></i>
        </button>
      </div>
      <div id="header">
        <h2>Machakos</h2>
        <p>{`${date.getDate()} - ${
          date.getMonth() + 1
        } - ${date.getFullYear()}`}</p>
        <div id="line" />
      </div>
      <div id="content">
        <div id="summary">
          <h1>Summary</h1>
          <div id="desc">
            <div id="transactions">
              <p>Transactions made</p>
              <p>10</p>
            </div>
            <div id="profit">
              <p>
                <i className="fa-solid fa-money-bill"></i>
              </p>
              <p>$ 100</p>
            </div>
          </div>
        </div>
        <div id="RecentTransactions">
          <h1>Transactions</h1>
          <div></div>
        </div>
        <div id="Stockup">
          <h1>Stock Up</h1>
          <div></div>
        </div>
      </div>
    </div>
  );
};

export default Branch;

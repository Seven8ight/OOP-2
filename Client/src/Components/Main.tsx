import { useNavigate } from "react-router";

const Main = (): React.ReactNode => {
  const navigation = useNavigate();

  return (
    <div id="Admin">
      <div id="back">
        <button onClick={() => navigation("/products")}>
          <i className="fa-solid fa-arrow-left"></i>
        </button>
      </div>
      <div id="content">
        <div id="header">
          <h1>Admin Dashboard</h1>
          <p>Nairobi HQ</p>
          <div id="line" />
        </div>
        <div id="info">
          <div id="summaryHQ">
            <h2>Summary</h2>
            <div>
              <div>
                <p id="desc">Number of transactions</p>
                <p id="amount">10</p>
              </div>
              <div>
                <p id="desc2">Profit made</p>
                <p id="amount2">10</p>
              </div>
            </div>
          </div>
          <div id="summaryBranches">
            <div id="Machakos">
              <header>
                <h3>Machakos</h3>
                <i
                  onClick={() => navigation("/management/branch/123")}
                  className="fa-solid fa-gear"
                ></i>
              </header>
              <div>
                <p>Number of transactions</p>
                <p>Stock available</p>
                <p>Profit made</p>
              </div>
            </div>
            <div id="Kisumu">
              <header>
                <h3>Kisumu</h3>
                <i
                  onClick={() => navigation("/management/branch/456")}
                  className="fa-solid fa-gear"
                ></i>
              </header>
              <div>
                <p>Number of transactions</p>
                <p>Stock available</p>
                <p>Profit made</p>
              </div>
            </div>
            <div id="Mombasa">
              <header>
                <h3>Mombasa</h3>
                <i
                  onClick={() => navigation("/settings")}
                  className="fa-solid fa-gear"
                ></i>
              </header>
              <div>
                <p>Number of transactions</p>
                <p>Stock available</p>
                <p>Profit made</p>
              </div>
            </div>
          </div>
          <div id="recentTransactions">
            <h3>Recent Transactions</h3>
            <div id="transactions"></div>
          </div>
          <div id="updateStock">
            <h3>Stock up</h3>
            <div id="stock"></div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default Main;

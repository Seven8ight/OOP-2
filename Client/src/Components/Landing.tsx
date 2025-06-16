import { useNavigate } from "react-router";

const Landing = (): React.ReactNode => {
  const navigation = useNavigate();

  return (
    <div id="Landing">
      <div id="intro">
        <div id="text">
          <h1>High rise</h1>
          <p>Providing the best drinks in town.</p>
        </div>
      </div>
      <div id="Options">
        <div id="text">
          <h2>Join in and get</h2>
          <p>Some benefits and features of joining us</p>
        </div>
        <div id="options">
          <div id="card">
            <div>
              <h1>Xquisite Drinks</h1>
              <p>Providing you the exclusive kind</p>
            </div>
            <ul>
              <li>Black label</li>
              <li>Black label</li>
              <li>Black label</li>
              <li>Black label</li>
              <li>Black label</li>
            </ul>
          </div>
          <div id="card">
            <div>
              <h1>All to none</h1>
              <p>In stock with any drink in the market</p>
            </div>

            <ul>
              <li>Black label</li>
              <li>Monster drink</li>
              <li>Red bull</li>
              <li>Coke</li>
            </ul>
          </div>
        </div>
        <div id="join">
          <button onClick={() => navigation("/accounts")}>Join us</button>
        </div>
      </div>
      <div id="footer">
        <div id="company">
          <h1>High Rise</h1>
          <p>Drinks all the way from the 17th Century</p>
        </div>
        <div id="contributions">
          <h3>Made with love by</h3>
          <ul>
            <li>Darlene Ondigi</li>
            <li>Danielle Ogutu</li>
            <li>Asiya Abdalla</li>
            <li>Mark Muriithi</li>
            <li>Lawrence Muchiri</li>
          </ul>
        </div>
      </div>
    </div>
  );
};

export default Landing;

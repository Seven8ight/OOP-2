import { useNavigate } from "react-router";

const User = (): React.ReactNode => {
  const navigation = useNavigate();

  return (
    <div>
      <button onClick={() => navigation("/products")}>Back</button>
      <h1>user page</h1>
    </div>
  );
};

export default User;

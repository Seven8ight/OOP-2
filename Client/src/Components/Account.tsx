import {
  Dispatch,
  Ref,
  SetStateAction,
  useEffect,
  useRef,
  useState,
} from "react";
import { useNavigate } from "react-router";
import Background from "./../Assets/joel-filipe-VuwAfoHpxgs-unsplash.jpg";

type Setter = {
  formRef: Ref<HTMLFormElement>;
  name: string;
  nameHandler: Dispatch<SetStateAction<string>>;
  email: string;
  emailHandler: Dispatch<SetStateAction<string>>;
  password: string;
  passHandler: Dispatch<SetStateAction<string>>;
  visibility: boolean;
  visibilityHandler: Dispatch<SetStateAction<boolean>>;
};

const Signup = ({
    formRef,
    name,
    nameHandler,
    email,
    emailHandler,
    password,
    passHandler,
    visibility,
    visibilityHandler,
  }: Setter): React.ReactNode => {
    return (
      <div id="signup">
        <h1>Signup</h1>
        <form ref={formRef}>
          <label htmlFor="name">Name</label>
          <br />
          <input
            id="name"
            type="text"
            name="name"
            placeholder="John Doe"
            required
            value={name}
            onChange={(event) => nameHandler(event.target.value)}
          />
          <br />
          <label htmlFor="email">Email</label>
          <br />
          <input
            name="email"
            type="email"
            id="email"
            placeholder="l@example.com"
            required
            value={email}
            onChange={(event) => emailHandler(event.target.value)}
          />
          <div id="passDiv">
            <label htmlFor="password">Password</label>
            <br />
            <input
              name="password"
              type={visibility ? "text" : "password"}
              id="password"
              required
              placeholder="some-strong stuff"
              value={password}
              onChange={(event) => passHandler(event.target.value)}
            />
            <div id="visibility" onClick={() => visibilityHandler(!visibility)}>
              {visibility && <i className="fa-solid fa-eye"></i>}
              {!visibility && <i className="fa-solid fa-eye-slash"></i>}
            </div>
          </div>
          <button type="submit">Submit</button>
        </form>
      </div>
    );
  },
  Login = ({
    formRef,
    email,
    emailHandler,
    password,
    passHandler,
    visibility,
    visibilityHandler,
  }: Omit<Setter, "name" | "nameHandler">): React.ReactNode => {
    return (
      <div id="login">
        <h1>Login</h1>
        <form ref={formRef}>
          <label htmlFor="email">Email</label>
          <br />
          <input
            type="email"
            id="email"
            name="email"
            placeholder="l@example.com"
            value={email}
            onChange={(event) => emailHandler(event.target.value)}
          />
          <div id="passDiv">
            <label htmlFor="password">Password</label>
            <br />
            <input
              type={visibility ? "text" : "password"}
              id="password"
              name="password"
              placeholder="some-strong stuff"
              value={password}
              onChange={(event) => passHandler(event.target.value)}
            />
            <div id="visibility">
              {visibility && (
                <i
                  onClick={() => visibilityHandler(true)}
                  className="fa-solid fa-eye"
                ></i>
              )}
              {!visibility && (
                <i
                  onClick={() => visibilityHandler(false)}
                  className="fa-solid fa-eye-slash"
                ></i>
              )}
            </div>
          </div>
          <button type="submit">Submit</button>
        </form>
      </div>
    );
  },
  Accounts = (): React.ReactNode => {
    const [form, setForm] = useState<"login" | "signup">("signup"),
      formHandler = (form: "login" | "signup") => setForm(form),
      [name, setName] = useState<string>(""),
      [email, setEmail] = useState<string>(""),
      [password, setPassword] = useState<string>(""),
      [visibility, visibilityHandler] = useState<boolean>(false),
      loginBtnRef = useRef<HTMLButtonElement>(null),
      signupBtnRef = useRef<HTMLButtonElement>(null),
      formRef = useRef<HTMLFormElement>(null),
      navigate = useNavigate();

    useEffect(() => {
      if (loginBtnRef.current && signupBtnRef.current) {
        if (form == "login") {
          if (signupBtnRef.current.classList.contains("active"))
            signupBtnRef.current.classList.remove("active");
          loginBtnRef.current.classList.add("active");
        } else {
          if (loginBtnRef.current.classList.contains("active"))
            loginBtnRef.current.classList.remove("active");
          signupBtnRef.current.classList.add("active");
        }

        setName("");
        setEmail("");
        setPassword("");
      }
    }, [form]);

    formRef.current?.addEventListener("submit", (event: SubmitEvent) => {
      event.preventDefault();

      const formData = new FormData(event.currentTarget as HTMLFormElement);
      let data: Record<string, any> = {};

      for (let [key, value] of formData.entries()) data[key] = value;

      navigate("/products");
    });

    return (
      <div id="Accounts">
        <img src={Background} />
        <div id="container">
          <div id="selector">
            <button ref={signupBtnRef} onClick={() => formHandler("signup")}>
              Sign Up
            </button>
            <button ref={loginBtnRef} onClick={() => formHandler("login")}>
              Log In
            </button>
          </div>
          <div id="form">
            {form == "login" && (
              <Login
                email={email}
                emailHandler={setEmail}
                password={password}
                passHandler={setPassword}
                formRef={formRef}
                visibility={visibility}
                visibilityHandler={visibilityHandler}
              />
            )}
            {form == "signup" && (
              <Signup
                name={name}
                nameHandler={setName}
                email={email}
                emailHandler={setEmail}
                password={password}
                passHandler={setPassword}
                formRef={formRef}
                visibility={visibility}
                visibilityHandler={visibilityHandler}
              />
            )}
          </div>
        </div>
      </div>
    );
  };

export default Accounts;

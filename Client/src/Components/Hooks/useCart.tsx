import { createContext, Dispatch, SetStateAction, useState } from "react";

type ProductContext = {
  products: product[];
  setProducts: Dispatch<SetStateAction<product[]>>;
};

type product = {
  id: string;
  name: string;
  quantity: number;
};

export const CartContext = createContext<ProductContext>({
    products: [],
    setProducts: () => {},
  }),
  useCart = () => {};

const Cart = () => {
  const [cart, setCart] = useState<product[]>([]);
  return (
    <CartContext.Provider
      value={{ products: cart, setProducts: setCart }}
    ></CartContext.Provider>
  );
};

export default Cart;

import { useEffect, useState } from "react";
import axios from "axios";

function Orders() {
  const [orders, setOrders] = useState([]);

  useEffect(() => {
    axios.get("http://localhost:8081/api/orders")
      .then(res => setOrders(res.data));
  }, []);

  return (
    <div style={{ padding: "20px" }}>
      <h2>Orders</h2>

      {orders.map((o) => (
        <div key={o.id} style={card}>
          <p>Order ID: {o.id}</p>
          <p>Medicine ID: {o.medicineId}</p>
          <p>Quantity: {o.quantity}</p>
        </div>
      ))}
    </div>
  );
}

const card = {
  border: "1px solid #ccc",
  padding: "10px",
  margin: "10px",
};

export default Orders;
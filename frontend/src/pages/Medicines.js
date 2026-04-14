import { useEffect, useState } from "react";
import axios from "axios";
import Navbar from "../components/Navbar";

function Medicines() {
  const [data, setData] = useState([]);

  useEffect(() => {
    axios.get("http://localhost:8081/api/medicines")
      .then(res => setData(res.data));
  }, []);

  const order = async (id) => {
    await axios.post("http://localhost:8081/api/orders", {
      medicineId: id,
      quantity: 1
    });
    alert("Ordered ✅");
  };

  return (
    <>
      <Navbar />
      <div style={container}>
        <h2>Medicines</h2>

        <div style={grid}>
          {data.map((m) => (
            <div key={m.id} style={card}>
              <h3>{m.name}</h3>
              <p>₹{m.price}</p>
              <p>Stock: {m.stockQuantity}</p>

              <button style={btn} onClick={() => order(m.id)}>
                Order Now
              </button>
            </div>
          ))}
        </div>
      </div>
    </>
  );
}

const container = { padding: "30px" };

const grid = {
  display: "grid",
  gridTemplateColumns: "repeat(auto-fill, minmax(250px, 1fr))",
  gap: "20px",
};

const card = {
  background: "white",
  padding: "20px",
  borderRadius: "12px",
  boxShadow: "0 5px 15px rgba(0,0,0,0.1)",
};

const btn = {
  marginTop: "10px",
  padding: "10px",
  background: "#667eea",
  color: "white",
  border: "none",
  borderRadius: "6px",
};

export default Medicines;
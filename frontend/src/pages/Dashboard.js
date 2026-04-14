import Navbar from "../components/Navbar";
import { useNavigate } from "react-router-dom";

function Dashboard() {
  const navigate = useNavigate();

  return (
    <>
      <Navbar />

      <div style={container}>
        <h1>Welcome 👋</h1>

        <div style={grid}>
          <div style={card} onClick={() => navigate("/medicines")}>
            💊 Medicines
          </div>

          <div style={card} onClick={() => navigate("/orders")}>
            📦 Orders
          </div>

          <div style={card} onClick={() => navigate("/inventory")}>
            📊 Inventory
          </div>

          <div style={card} onClick={() => navigate("/prescriptions")}>
            📄 Prescriptions
          </div>
        </div>
      </div>
    </>
  );
}

const container = { padding: "30px" };

const grid = {
  display: "grid",
  gridTemplateColumns: "repeat(auto-fit, minmax(200px, 1fr))",
  gap: "20px",
  marginTop: "20px",
};

const card = {
  background: "white",
  padding: "30px",
  borderRadius: "12px",
  textAlign: "center",
  fontSize: "18px",
  cursor: "pointer",
  boxShadow: "0 5px 15px rgba(0,0,0,0.1)",
};

export default Dashboard;
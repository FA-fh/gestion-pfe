import { BrowserRouter, NavLink, Route, Routes } from "react-router-dom";
import Dashboard from "./pages/Dashboard";
import Filieres from "./pages/Filieres";
import Etudiants from "./pages/Etudiants";
import Entreprises from "./pages/Entreprises";
import Stages from "./pages/Stages";
import "./App.css";
import EncadrantsAcademiques from "./pages/EncadrantsAcademiques";
import EncadrantsEntreprise from "./pages/EncadrantsEntreprise";
import ResponsablesFilieres from "./pages/ResponsablesFilieres";

function App() {
  return (
    <BrowserRouter>
      <div className="app">
        <aside className="sidebar">
          <h2>Gestion PFE</h2>

          <nav>
            <NavLink to="/">Dashboard</NavLink>
            <NavLink to="/filieres">Filières</NavLink>
            <NavLink to="/etudiants">Étudiants</NavLink>
            <NavLink to="/entreprises">Entreprises</NavLink>
            <NavLink to="/stages">Stages PFE</NavLink>
			<NavLink to="/encadrants-academiques">Encadrants académiques</NavLink>
			<NavLink to="/encadrants-entreprise">Encadrants entreprise</NavLink>
			<NavLink to="/responsables-filieres">Responsables filières</NavLink>
          </nav>
        </aside>

        <main className="content">
          <Routes>
            <Route path="/" element={<Dashboard />} />
            <Route path="/filieres" element={<Filieres />} />
            <Route path="/etudiants" element={<Etudiants />} />
            <Route path="/entreprises" element={<Entreprises />} />
            <Route path="/stages" element={<Stages />} />
			<Route path="/encadrants-academiques" element={<EncadrantsAcademiques />} />
			<Route path="/encadrants-entreprise" element={<EncadrantsEntreprise />} />
			<Route path="/responsables-filieres" element={<ResponsablesFilieres />} />
          </Routes>
        </main>
      </div>
    </BrowserRouter>
  );
}

export default App;
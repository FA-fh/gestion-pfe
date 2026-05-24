import { useEffect, useState } from "react";
import api from "../api/api";

function Dashboard() {
  const [stats, setStats] = useState(null);

  useEffect(() => {
    api.get("/dashboard/stats")
      .then((response) => setStats(response.data))
      .catch((error) => console.error(error));
  }, []);

  if (!stats) {
    return <p>Chargement...</p>;
  }

  return (
    <div>
      <h1>Tableau de bord</h1>

      <div className="stats-grid">
        <div className="stat-card">
          <span>Total stages</span>
          <strong>{stats.totalStages}</strong>
        </div>

        <div className="stat-card">
          <span>Total étudiants</span>
          <strong>{stats.totalEtudiants}</strong>
        </div>

        <div className="stat-card">
          <span>Total entreprises</span>
          <strong>{stats.totalEntreprises}</strong>
        </div>

        <div className="stat-card">
          <span>Total filières</span>
          <strong>{stats.totalFilieres}</strong>
        </div>

        <div className="stat-card">
          <span>Encadrants académiques</span>
          <strong>{stats.totalEncadrantsAcademiques}</strong>
        </div>

        <div className="stat-card">
          <span>Encadrants entreprise</span>
          <strong>{stats.totalEncadrantsEntreprises}</strong>
        </div>
      </div>

      <div className="section-grid">
        <section className="panel">
          <h2>Stages par année</h2>
          <table>
            <thead>
              <tr>
                <th>Année</th>
                <th>Total</th>
              </tr>
            </thead>
            <tbody>
              {stats.stagesParAnnee.map((item) => (
                <tr key={item.annee}>
                  <td>{item.annee}</td>
                  <td>{item.total}</td>
                </tr>
              ))}
            </tbody>
          </table>
        </section>

        <section className="panel">
          <h2>Stages par filière</h2>
          <table>
            <thead>
              <tr>
                <th>Filière</th>
                <th>Total</th>
              </tr>
            </thead>
            <tbody>
              {stats.stagesParFiliere.map((item) => (
                <tr key={item.filiereId}>
                  <td>{item.filiere}</td>
                  <td>{item.total}</td>
                </tr>
              ))}
            </tbody>
          </table>
        </section>
      </div>
    </div>
  );
}

export default Dashboard;
import { useEffect, useState } from "react";
import api from "../api/api";

function Stages() {
  const [stages, setStages] = useState([]);
  const [etudiants, setEtudiants] = useState([]);
  const [entreprises, setEntreprises] = useState([]);
  const [encadrantsAcademiques, setEncadrantsAcademiques] = useState([]);
  const [encadrantsEntreprise, setEncadrantsEntreprise] = useState([]);
  const [filieres, setFilieres] = useState([]);
  const [editingId, setEditingId] = useState(null);

  const [filters, setFilters] = useState({
    filiereId: "",
    annee: "",
  });

  const [form, setForm] = useState({
    sujet: "",
    dateDebut: "",
    dateFin: "",
    description: "",
    objectifs: "",
    solution: "",
    demarche: "",
    outils: "",
    annee: "",
    etudiantId: "",
    entrepriseId: "",
    encadrantEntrepriseId: "",
    encadrantAcademiqueId: "",
  });

  const loadData = () => {
    api.get("/stages").then((response) => setStages(response.data));
    api.get("/etudiants").then((response) => setEtudiants(response.data));
    api.get("/entreprises").then((response) => setEntreprises(response.data));
    api.get("/encadrants-academiques").then((response) => setEncadrantsAcademiques(response.data));
    api.get("/encadrants-entreprise").then((response) => setEncadrantsEntreprise(response.data));
    api.get("/filieres").then((response) => setFilieres(response.data));
  };

  useEffect(() => {
    loadData();
  }, []);

  const handleChange = (e) => {
    setForm({
      ...form,
      [e.target.name]: e.target.value,
    });
  };

  const handleFilterChange = (e) => {
    setFilters({
      ...filters,
      [e.target.name]: e.target.value,
    });
  };

  const resetForm = () => {
    setForm({
      sujet: "",
      dateDebut: "",
      dateFin: "",
      description: "",
      objectifs: "",
      solution: "",
      demarche: "",
      outils: "",
      annee: "",
      etudiantId: "",
      entrepriseId: "",
      encadrantEntrepriseId: "",
      encadrantAcademiqueId: "",
    });
    setEditingId(null);
  };

  const buildStageData = () => ({
    sujet: form.sujet,
    dateDebut: form.dateDebut,
    dateFin: form.dateFin,
    description: form.description,
    objectifs: form.objectifs,
    solution: form.solution,
    demarche: form.demarche,
    outils: form.outils,
    annee: Number(form.annee),
    etudiant: form.etudiantId ? { id: Number(form.etudiantId) } : null,
    entreprise: form.entrepriseId ? { id: Number(form.entrepriseId) } : null,
    encadrantEntreprise: form.encadrantEntrepriseId
      ? { id: Number(form.encadrantEntrepriseId) }
      : null,
    encadrantAcademique: form.encadrantAcademiqueId
      ? { id: Number(form.encadrantAcademiqueId) }
      : null,
  });

  const handleSubmit = (e) => {
    e.preventDefault();

    const data = buildStageData();

    const request = editingId
      ? api.put(`/stages/${editingId}`, data)
      : api.post("/stages", data);

    request.then(() => {
      resetForm();
      loadData();
    });
  };

  const handleEdit = (stage) => {
    setEditingId(stage.id);
    setForm({
      sujet: stage.sujet || "",
      dateDebut: stage.dateDebut || "",
      dateFin: stage.dateFin || "",
      description: stage.description || "",
      objectifs: stage.objectifs || "",
      solution: stage.solution || "",
      demarche: stage.demarche || "",
      outils: stage.outils || "",
      annee: stage.annee || "",
      etudiantId: stage.etudiant?.id || "",
      entrepriseId: stage.entreprise?.id || "",
      encadrantEntrepriseId: stage.encadrantEntreprise?.id || "",
      encadrantAcademiqueId: stage.encadrantAcademique?.id || "",
    });
  };

  const handleDelete = (id) => {
    if (window.confirm("Supprimer ce stage ?")) {
      api.delete(`/stages/${id}`).then(() => loadData());
    }
  };

  const searchStages = () => {
    const params = new URLSearchParams();

    if (filters.filiereId) {
      params.append("filiereId", filters.filiereId);
    }

    if (filters.annee) {
      params.append("annee", filters.annee);
    }

    api.get(`/stages/search?${params.toString()}`)
      .then((response) => setStages(response.data));
  };

  const resetFilters = () => {
    setFilters({ filiereId: "", annee: "" });
    loadData();
  };

  const downloadPdf = (id) => {
    window.open(`http://localhost:8080/api/stages/${id}/rapport`, "_blank");
  };

  return (
    <div>
      <h1>Gestion des stages PFE</h1>

      <section className="panel">
        <h2>{editingId ? "Modifier un stage" : "Ajouter un stage"}</h2>

        <form className="form vertical-form" onSubmit={handleSubmit}>
          <input name="sujet" placeholder="Sujet du stage" value={form.sujet} onChange={handleChange} required />

          <input name="dateDebut" type="date" value={form.dateDebut} onChange={handleChange} required />
          <input name="dateFin" type="date" value={form.dateFin} onChange={handleChange} required />

          <input name="annee" type="number" placeholder="Année" value={form.annee} onChange={handleChange} required />

          <select name="etudiantId" value={form.etudiantId} onChange={handleChange} required>
            <option value="">Choisir un étudiant</option>
            {etudiants.map((etudiant) => (
              <option key={etudiant.id} value={etudiant.id}>
                {etudiant.nom} {etudiant.prenom}
              </option>
            ))}
          </select>

          <select name="entrepriseId" value={form.entrepriseId} onChange={handleChange} required>
            <option value="">Choisir une entreprise</option>
            {entreprises.map((entreprise) => (
              <option key={entreprise.id} value={entreprise.id}>
                {entreprise.nom}
              </option>
            ))}
          </select>

          <select name="encadrantEntrepriseId" value={form.encadrantEntrepriseId} onChange={handleChange}>
            <option value="">Choisir un encadrant entreprise</option>
            {encadrantsEntreprise.map((encadrant) => (
              <option key={encadrant.id} value={encadrant.id}>
                {encadrant.nom} {encadrant.prenom}
              </option>
            ))}
          </select>

          <select name="encadrantAcademiqueId" value={form.encadrantAcademiqueId} onChange={handleChange}>
            <option value="">Choisir un encadrant académique</option>
            {encadrantsAcademiques.map((encadrant) => (
              <option key={encadrant.id} value={encadrant.id}>
                {encadrant.nom} {encadrant.prenom}
              </option>
            ))}
          </select>

          <textarea name="description" placeholder="Description détaillée" value={form.description} onChange={handleChange} />
          <textarea name="objectifs" placeholder="Objectifs" value={form.objectifs} onChange={handleChange} />
          <textarea name="solution" placeholder="Solution" value={form.solution} onChange={handleChange} />
          <textarea name="demarche" placeholder="Démarche" value={form.demarche} onChange={handleChange} />
          <textarea name="outils" placeholder="Outils utilisés" value={form.outils} onChange={handleChange} />

          <div>
            <button type="submit">{editingId ? "Modifier" : "Ajouter"}</button>

            {editingId && (
              <button type="button" className="secondary" onClick={resetForm}>
                Annuler
              </button>
            )}
          </div>
        </form>
      </section>

      <section className="panel">
        <h2>Recherche</h2>

        <div className="form">
          <select name="filiereId" value={filters.filiereId} onChange={handleFilterChange}>
            <option value="">Toutes les filières</option>
            {filieres.map((filiere) => (
              <option key={filiere.id} value={filiere.id}>
                {filiere.intitule}
              </option>
            ))}
          </select>

          <input
            name="annee"
            type="number"
            placeholder="Année"
            value={filters.annee}
            onChange={handleFilterChange}
          />

          <button type="button" onClick={searchStages}>Rechercher</button>
          <button type="button" className="secondary" onClick={resetFilters}>Réinitialiser</button>
        </div>
      </section>

      <section className="panel">
        <h2>Liste des stages</h2>

        <table>
          <thead>
            <tr>
              <th>Sujet</th>
              <th>Étudiant</th>
              <th>Filière</th>
              <th>Entreprise</th>
              <th>Année</th>
              <th>Actions</th>
            </tr>
          </thead>

          <tbody>
            {stages.map((stage) => (
              <tr key={stage.id}>
                <td>{stage.sujet}</td>
                <td>{stage.etudiant?.nom} {stage.etudiant?.prenom}</td>
                <td>{stage.etudiant?.filiere?.intitule}</td>
                <td>{stage.entreprise?.nom}</td>
                <td>{stage.annee}</td>
                <td>
                  <button onClick={() => handleEdit(stage)}>Modifier</button>
                  <button onClick={() => downloadPdf(stage.id)}>PDF</button>
                  <button className="danger" onClick={() => handleDelete(stage.id)}>
                    Supprimer
                  </button>
                </td>
              </tr>
            ))}

            {stages.length === 0 && (
              <tr>
                <td colSpan="6">Aucun stage trouvé.</td>
              </tr>
            )}
          </tbody>
        </table>
      </section>
    </div>
  );
}

export default Stages;
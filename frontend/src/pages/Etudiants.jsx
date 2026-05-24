import { useEffect, useState } from "react";
import api from "../api/api";

function Etudiants() {
  const [etudiants, setEtudiants] = useState([]);
  const [filieres, setFilieres] = useState([]);
  const [editingId, setEditingId] = useState(null);

  const [form, setForm] = useState({
    cne: "",
    nom: "",
    prenom: "",
    email: "",
    telephone: "",
    filiereId: "",
  });

  const loadData = () => {
    api.get("/etudiants").then((response) => setEtudiants(response.data));
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

  const resetForm = () => {
    setForm({
      cne: "",
      nom: "",
      prenom: "",
      email: "",
      telephone: "",
      filiereId: "",
    });
    setEditingId(null);
  };

  const handleSubmit = (e) => {
    e.preventDefault();

    const data = {
      cne: form.cne,
      nom: form.nom,
      prenom: form.prenom,
      email: form.email,
      telephone: form.telephone,
      filiere: form.filiereId ? { id: Number(form.filiereId) } : null,
    };

    const request = editingId
      ? api.put(`/etudiants/${editingId}`, data)
      : api.post("/etudiants", data);

    request.then(() => {
      resetForm();
      loadData();
    });
  };

  const handleEdit = (etudiant) => {
    setEditingId(etudiant.id);
    setForm({
      cne: etudiant.cne || "",
      nom: etudiant.nom || "",
      prenom: etudiant.prenom || "",
      email: etudiant.email || "",
      telephone: etudiant.telephone || "",
      filiereId: etudiant.filiere?.id || "",
    });
  };

  const handleDelete = (id) => {
    if (window.confirm("Supprimer cet étudiant ?")) {
      api.delete(`/etudiants/${id}`).then(() => loadData());
    }
  };

  return (
    <div>
      <h1>Gestion des étudiants</h1>

      <section className="panel">
        <h2>{editingId ? "Modifier un étudiant" : "Ajouter un étudiant"}</h2>

        <form className="form" onSubmit={handleSubmit}>
          <input name="cne" placeholder="CNE" value={form.cne} onChange={handleChange} required />
          <input name="nom" placeholder="Nom" value={form.nom} onChange={handleChange} required />
          <input name="prenom" placeholder="Prénom" value={form.prenom} onChange={handleChange} required />
          <input name="email" placeholder="Email" value={form.email} onChange={handleChange} required />
          <input name="telephone" placeholder="Téléphone" value={form.telephone} onChange={handleChange} />

          <select name="filiereId" value={form.filiereId} onChange={handleChange} required>
            <option value="">Choisir une filière</option>
            {filieres.map((filiere) => (
              <option key={filiere.id} value={filiere.id}>
                {filiere.intitule}
              </option>
            ))}
          </select>

          <button type="submit">{editingId ? "Modifier" : "Ajouter"}</button>

          {editingId && (
            <button type="button" className="secondary" onClick={resetForm}>
              Annuler
            </button>
          )}
        </form>
      </section>

      <section className="panel">
        <h2>Liste des étudiants</h2>

        <table>
          <thead>
            <tr>
              <th>CNE</th>
              <th>Nom complet</th>
              <th>Email</th>
              <th>Téléphone</th>
              <th>Filière</th>
              <th>Actions</th>
            </tr>
          </thead>

          <tbody>
            {etudiants.map((etudiant) => (
              <tr key={etudiant.id}>
                <td>{etudiant.cne}</td>
                <td>{etudiant.nom} {etudiant.prenom}</td>
                <td>{etudiant.email}</td>
                <td>{etudiant.telephone}</td>
                <td>{etudiant.filiere?.intitule}</td>
                <td>
                  <button onClick={() => handleEdit(etudiant)}>Modifier</button>
                  <button className="danger" onClick={() => handleDelete(etudiant.id)}>
                    Supprimer
                  </button>
                </td>
              </tr>
            ))}

            {etudiants.length === 0 && (
              <tr>
                <td colSpan="6">Aucun étudiant trouvé.</td>
              </tr>
            )}
          </tbody>
        </table>
      </section>
    </div>
  );
}

export default Etudiants;
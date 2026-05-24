import { useEffect, useState } from "react";
import api from "../api/api";

function ResponsablesFilieres() {
  const [responsables, setResponsables] = useState([]);
  const [filieres, setFilieres] = useState([]);
  const [editingId, setEditingId] = useState(null);

  const [form, setForm] = useState({
    nom: "",
    prenom: "",
    grade: "",
    email: "",
    telephone: "",
    filiereId: "",
  });

  const loadData = () => {
    api.get("/responsables-filieres")
      .then((response) => setResponsables(response.data));

    api.get("/filieres")
      .then((response) => setFilieres(response.data));
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
      nom: "",
      prenom: "",
      grade: "",
      email: "",
      telephone: "",
      filiereId: "",
    });
    setEditingId(null);
  };

  const buildData = () => ({
    nom: form.nom,
    prenom: form.prenom,
    grade: form.grade,
    email: form.email,
    telephone: form.telephone,
    filiere: form.filiereId ? { id: Number(form.filiereId) } : null,
  });

  const handleSubmit = (e) => {
    e.preventDefault();

    const data = buildData();

    const request = editingId
      ? api.put(`/responsables-filieres/${editingId}`, data)
      : api.post("/responsables-filieres", data);

    request.then(() => {
      resetForm();
      loadData();
    });
  };

  const handleEdit = (responsable) => {
    setEditingId(responsable.id);
    setForm({
      nom: responsable.nom || "",
      prenom: responsable.prenom || "",
      grade: responsable.grade || "",
      email: responsable.email || "",
      telephone: responsable.telephone || "",
      filiereId: responsable.filiere?.id || "",
    });
  };

  const handleDelete = (id) => {
    if (window.confirm("Supprimer ce responsable de filière ?")) {
      api.delete(`/responsables-filieres/${id}`).then(() => loadData());
    }
  };

  return (
    <div>
      <h1>Responsables filières</h1>

      <section className="panel">
        <h2>{editingId ? "Modifier un responsable" : "Ajouter un responsable"}</h2>

        <form className="form" onSubmit={handleSubmit}>
          <input name="nom" placeholder="Nom" value={form.nom} onChange={handleChange} required />
          <input name="prenom" placeholder="Prénom" value={form.prenom} onChange={handleChange} required />
          <input name="grade" placeholder="Grade" value={form.grade} onChange={handleChange} />
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
        <h2>Liste des responsables filières</h2>

        <table>
          <thead>
            <tr>
              <th>Nom complet</th>
              <th>Grade</th>
              <th>Email</th>
              <th>Téléphone</th>
              <th>Filière</th>
              <th>Actions</th>
            </tr>
          </thead>

          <tbody>
            {responsables.map((responsable) => (
              <tr key={responsable.id}>
                <td>{responsable.nom} {responsable.prenom}</td>
                <td>{responsable.grade}</td>
                <td>{responsable.email}</td>
                <td>{responsable.telephone}</td>
                <td>{responsable.filiere?.intitule}</td>
                <td>
                  <button onClick={() => handleEdit(responsable)}>Modifier</button>
                  <button className="danger" onClick={() => handleDelete(responsable.id)}>
                    Supprimer
                  </button>
                </td>
              </tr>
            ))}

            {responsables.length === 0 && (
              <tr>
                <td colSpan="6">Aucun responsable de filière trouvé.</td>
              </tr>
            )}
          </tbody>
        </table>
      </section>
    </div>
  );
}

export default ResponsablesFilieres;
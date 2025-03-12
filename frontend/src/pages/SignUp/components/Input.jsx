export function Input(props) {
  const { id, label, onChange, error, value, type } = props;

  return (
    <div className="mb-2">
      <label htmlFor={id} className="form-label ">
        {label}
      </label>
      <input
        id={id}
        className={error ? "form-control is-invalid " : "form-control"}
        onChange={onChange}
        type={type}
        value={value}
      />
      <div className="invalid-feedback">{error}</div>
    </div>
  );
}

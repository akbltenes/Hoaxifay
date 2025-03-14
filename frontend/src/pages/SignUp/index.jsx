import { useEffect, useMemo, useState } from "react";
import { signUp } from "./api";
import { Input } from "./components/Input";
import { useTranslation } from "react-i18next";
import { LanguageSelector } from "../../shared/components/LanguageSelector";

export function SignUp() {
  const [username, setUsername] = useState();
  const [email, setEmail] = useState();
  const [password, setPassword] = useState();
  const [passwordRepeat, setPasswordRepeat] = useState();
  const [apiProgress, setApiProgress] = useState(false);
  const [successMessage, setSuccessMessage] = useState();
  const [errors, setErrors] = useState({});
  const [generalError, setGeneralError] = useState();
  const { t } = useTranslation();
  useEffect(() => {
    setErrors(function (lastErrors) {
      return { ...lastErrors, username: undefined };
    });
  }, [username]);

  useEffect(() => {
    setErrors(function (lastErrors) {
      return { ...lastErrors, email: undefined };
    });
  }, [email]);

  useEffect(() => {
    setErrors(function (lastErrors) {
      return { ...lastErrors, password: undefined };
    });
  }, [password]);

  const onSubmit = async (event) => {
    event.preventDefault();
    setSuccessMessage();
    setGeneralError();
    setApiProgress(true);
    try {
      const response = await signUp({
        username,
        email,
        password,
        passwordRepeat,
      });
      setSuccessMessage(response.data.message);
    } catch (axiosError) {
      if (
        axiosError.response?.data &&
        axiosError.response.data.status === 400
      ) {
        setErrors(axiosError.response.data.validationErrors);
      } else {
        setGeneralError(t("generalError"));
      }
    } finally {
      setApiProgress(false);
    }
  };

  const passwordRepeatError = useMemo(() => {
    if (password && password !== passwordRepeat) {
      return t("passwordMismatch");
    }
    return "";
  }, [password, passwordRepeat]);

  return (
    <div className="container">
      <div className="col-lg-6 offset-lg-3 col-sm-8 offset-sm-2">
        <form className="card" onSubmit={onSubmit}>
          <div
            className="text-center card-header bg-dark text-white "
            style={{ width: "100%" }}
          >
            <h1>{t("signUp")}</h1>
          </div>
          <div className="card-body ">
            <Input
              id="username"
              label={t("username")}
              value={username}
              onChange={(event) => setUsername(event.target.value)}
              error={errors.username}
            />

            <Input
              id="email"
              label={t("email")}
              value={email}
              onChange={(event) => setEmail(event.target.value)}
              error={errors.email}
            />

            <Input
              id="password"
              label={t("password")}
              value={password}
              type="password"
              onChange={(event) => setPassword(event.target.value)}
              error={errors.password}
            />

            <Input
              id="passwordRepeat"
              label={t("passwordRepeat")}
              type="password"
              value={passwordRepeat}
              onChange={(event) => setPasswordRepeat(event.target.value)}
              error={passwordRepeatError}
            />

            {/*backende gonder*/}
            {successMessage && (
              <div className="alert alert-success">{successMessage}</div>
            )}
            {generalError && (
              <div className="alert alert-danger">{generalError}</div>
            )}
            <div className="text-center">
              <button
                className="btn btn-success"
                type="submit"
                disabled={apiProgress || password !== passwordRepeat}
              >
                {apiProgress && (
                  <span
                    className="spinner-border spinner-border-sm"
                    aria-hidden="true"
                  ></span>
                )}
                <span className="visually-hidden" role="status">
                  Loading...
                </span>
                Sign Up
              </button>
            </div>
          </div>
        </form>
        <LanguageSelector />
      </div>
    </div>
  );
}

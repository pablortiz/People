@echo off
setlocal enabledelayedexpansion

:: Nombre del archivo de salida
set OUTPUT_FILE=contenido_completo.txt

:: Borrar archivo de salida si ya existe
del "%OUTPUT_FILE%" 2>nul

:: Recorre todos los archivos en los subdirectorios, excluyendo el directorio actual
for /r %%F in (*) do (
    set "relative_path=%%F"
    for %%D in ("%%~dpF..") do if not "%%D"=="%CD%" (
        echo ==== %%F ==== >> "%OUTPUT_FILE%"
        type "%%F" >> "%OUTPUT_FILE%"
        echo. >> "%OUTPUT_FILE%"
    )
)

echo Archivo "%OUTPUT_FILE%" generado con éxito.
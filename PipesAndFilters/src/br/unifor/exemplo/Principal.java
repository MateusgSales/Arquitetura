package br.unifor.exemplo;

import br.unifor.datasink.DataSink;
import br.unifor.datasource.*;
import br.unifor.filter.Filter;
import br.unifor.pipe.Pipe;
import br.unifor.pipe.PipeImpl;

public class Principal {
    public static void main(String[] args) {
        // Cria os pipes
        final Pipe<String> pipeDataSource = new PipeImpl<String>();
        final Pipe<String> pipeDataSink = new PipeImpl<String>();
        final Pipe<String> pipeSigla = new PipeImpl<String>();

        // Cria os componentes que usam os pipes
        final DataSource<String> dataSource = new ExemploDataSource(pipeDataSource);
        final Filter<String, String> filterUpper = new ExemploFilterUpperCase(pipeDataSource, pipeSigla);
        final Filter<String, String> filterSigla = new ExemploFilterSigla(pipeSigla, pipeDataSink);
        final DataSink<String> dataSink = new ExemploDataSink(pipeDataSink);


        // Inicia todos os componentes
        dataSource.start();
        filterUpper.start();
        filterSigla.start();
        dataSink.start();
        
    }
}